import { OrderItem } from './OrderItem';

export interface PurchaseOrder {
  id: number;
  dateOfCreation: Date;
  estimatedDate: Date;
  dateOfArrival: Date;
  totalPrice: number;
  status: string;
  orderItems: OrderItem[];
}
