import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { clientId } from 'src/app/constants/paypal';
declare var paypal: any;
@Component({
  selector: 'app-pay-order',
  templateUrl: './pay-order.component.html',
  styleUrls: ['./pay-order.component.css'],
})
export class PayOrderComponent {
  paymentForm: FormGroup;
  constructor(
    @Inject(MAT_DIALOG_DATA) public totalPrice: number,
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<PayOrderComponent>
  ) {
    this.paymentForm = this.fb.group({
      holder: ['', Validators.required],
      cardNumber: ['', Validators.required],
      securityCode: ['', Validators.required],
      expirationDate: ['', Validators.required],
    });
  }

  get holder() {
    return this.paymentForm.get('holder');
  }
  get cardNumber() {
    return this.paymentForm.get('cardNumber');
  }
  get securityCode() {
    return this.paymentForm.get('securityCode');
  }
  get expirationDate() {
    return this.paymentForm.get('expirationDate');
  }

  ngOnInit() {
    this.loadPayPalScript();
  }

  loadPayPalScript() {
    const script = document.createElement('script');
    script.src = 'https://www.paypal.com/sdk/js?client-id=' + clientId;
    script.async = true;
    script.onload = () => {
      this.initializePayPalButton();
    };
    document.head.appendChild(script);
  }

  initializePayPalButton() {
    paypal
      .Buttons({
        createOrder: (data: any, actions: any) => {
          return actions.order.create({
            purchase_units: [
              {
                amount: {
                  value: (this.totalPrice * 0.56).toPrecision(2),
                },
              },
            ],
          });
        },
        onApprove: (data: any, actions: any) => {
          return actions.order.capture().then((details: any) => {
            console.log('Payment completed:', details);
            this.dialogRef.close(true);
          });
        },
      })
      .render('#paypal-button-container');
  }

  onCancelClick() {
    this.dialogRef.close(false);
  }
}
