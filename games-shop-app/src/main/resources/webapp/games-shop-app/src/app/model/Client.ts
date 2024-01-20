import { ShoppingCartItem } from './ShoppingCartItem';

export interface Client {
  username: string;
  password: string;
  shoppingCardItems: ShoppingCartItem[];
}
