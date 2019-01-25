import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { Observable, config } from 'rxjs';
import { HttpClient } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    
  })
};


@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor( private http: HttpClient) { }

  url2: string = "/signup/token";

  getToken (json): Observable<any> {
    console.log("inside getToken");
    console.log("inside getToken:"+json);
    return this.http.post<any>(this.url2, json, httpOptions);
  }
}