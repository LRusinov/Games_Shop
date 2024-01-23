import { ShoppingCartItem } from './ShoppingCartItem';

export interface Client {
  username: string;
  password: string;
  role: string;
  shoppingCardItems: ShoppingCartItem[];
}
export class User {
  public name: string;
  public username: string;
  public password: string;
  public role: string;
  public statusCd: string;
  public statusMsg: string;
  public authStatus: string;

  constructor(
    name?: string,
    username?: string,
    password?: string,
    role?: string,
    statusCd?: string,
    statusMsg?: string,
    authStatus?: string
  ) {
    this.name = name || '';
    this.username = username || '';
    this.password = password || '';
    this.role = role || '';
    this.authStatus = authStatus || '';
    this.statusCd = statusCd || '';
    this.statusMsg = statusMsg || '';
    this.authStatus = authStatus || '';
  }
}
