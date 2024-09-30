import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Knowledge } from './knowledge';

@Injectable({
  providedIn: 'root'
})
export class KnowledgeService {
  constructor(private http: HttpClient) {}

  private baseUrl = 'http://secret-app-jk-back-jakubkubiak47-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com/api/knowledge';

  getAllKnowledges(): Observable<Knowledge[]> {
    return this.http.get<Knowledge[]>(`${this.baseUrl}`);
  }

  searchKnowledge(term: string): Observable<Knowledge[]> {
    return this.http.get<Knowledge[]>(`${this.baseUrl}/search?term=${term}`);
  }

  createKnowledge(knowledge: Knowledge): Observable<Knowledge> {
    return this.http.post<Knowledge>(this.baseUrl, knowledge);
  }

  updateKnowledge(id: number, knowledge: Knowledge): Observable<Knowledge> {
    return this.http.put<Knowledge>(`${this.baseUrl}/${id}`, knowledge);
  }

  deleteKnowledge(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
