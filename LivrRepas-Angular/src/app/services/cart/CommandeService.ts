import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CommandeService {
  private apiUrl = 'http://localhost:8089/api/user'; // Replace with your backend URL

  constructor(private http: HttpClient) {}

  creerCommande(utilisateurId: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/commander/${utilisateurId}`, {});
  }
}
