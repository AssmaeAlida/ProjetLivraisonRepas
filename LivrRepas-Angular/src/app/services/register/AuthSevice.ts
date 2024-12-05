import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators'; // Import tap operator

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private baseUrl = 'http://localhost:8089/api/user';

  constructor(private http: HttpClient) {}

  signUp(email: string, password: string): Observable<number> {
    const payload = { email, password };
    return this.http.post<number>(`${this.baseUrl}/signUp`, payload);
  }

  signIn(email: string, password: string): Observable<any> {
    const payload = { email, password };
    return this.http.post<any>(`${this.baseUrl}/signIn`, payload).pipe(
      tap((response) => {
        // Save userId to localStorage if it exists
        if (response?.userId) {
          localStorage.setItem('userId', response.userId.toString());
          localStorage.setItem('isLoggedIn', 'true');
          console.log('Logged in user ID:', response.userId); // Log user ID to console
        }
      })
    );
  }
  

  signOut(): void {
    localStorage.removeItem('isLoggedIn');
    localStorage.removeItem('userId');
  }

  isLoggedIn(): boolean {
    return localStorage.getItem('isLoggedIn') === 'true';
  }

  getUserId(): number | null {
    const userId = localStorage.getItem('userId');
    return userId ? parseInt(userId, 10) : null;
  }
}
