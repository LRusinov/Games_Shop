import { ShoppingCartItem } from './ShoppingCartItem';

export interface Client {
  username: string;
  password: string;
  role: string;
  shoppingCardItems: ShoppingCartItem[];
}
export class User {
  public name: string;
  public password: string;
  public role: string;
  public authStatus: string;

  constructor(
    name?: string,
    password?: string,
    role?: string,
    authStatus?: string
  ) {
    this.name = name || '';
    this.password = password || '';
    this.role = role || '';
    this.authStatus = authStatus || '';
  }
}
