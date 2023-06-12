import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { access } from 'fs';

const USER_KEY = 'auth-user';
@Injectable({
  providedIn: 'root'
})
export class StorageService {
  cookie_name = 'cookie'
  value = this.authService.getToken();
  constructor(private authService: AuthService) { }
  //clear session user infos
  clean(): void {
    window.sessionStorage.clear();
    window.localStorage.clear();
  }

  public saveUser(user: any): void {
    console.log(this.value)
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }


  public getUser(): any {
    const user = window.sessionStorage.getItem(USER_KEY);
    if (user) {
      return JSON.parse(user);
    }

    return {};
  }


  public isLoggedIn(): boolean {
    const user = window.sessionStorage.getItem(USER_KEY);
    if (user) {
      return true;
    }

    return false;
  }

}
