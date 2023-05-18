import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { User } from 'src/app/interfaces/users';
export interface AuthResponse {
  jwt: string;
}
const AUTH_API = 'http://localhost:8080/api/auth/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private tokenSubject = new BehaviorSubject<string | null>(null);

  constructor(private http: HttpClient) {
    const token = localStorage.getItem('jwt');
    if (token) {
      this.tokenSubject.next(token);
    }
  }

  login(username: string, password: string): Observable<AuthResponse> {
    return this.http
      .post<AuthResponse>(AUTH_API + 'signin', { username, password })
      .pipe(
        tap((response) => {
          localStorage.setItem('jwt', response.jwt);
          this.tokenSubject.next(response.jwt);
        })
      );
  }
  gettToken(): string | null {
    return this.tokenSubject.value;
  }

  register(username: string, email: string, password: string, region: string, tel: number): Observable<any> {
    return this.http.post(
      AUTH_API + 'signup',
      {
        username,
        email,
        password,
        region,
        tel
      },
      httpOptions
    );
  }


  logout(): Observable<any> {
    return this.http.post(AUTH_API + 'signout', {}, httpOptions);
  }
  getToken(): Observable<String> {
    return this.http.get(AUTH_API + 'readCookie', { responseType: 'text' });
  }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(AUTH_API + 'getAllUsers');
  }
  updateUser(user: User): Observable<User> {
    return this.http.put<User>(`${AUTH_API}editUser/${user.id}`, user, httpOptions);
  }
  deleteUser(userId: number): Observable<void> {
    return this.http.delete<void>(`${AUTH_API}deleteUser/${userId}`, httpOptions);
  }

}
