import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, config } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';


const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
  })
};



@Injectable({
  providedIn: 'root'
})
export class SignupService {


  url1: string = "/signup/signup/adduser";
  url2: string = "/signup/showusers";
  url3: string = "/signup/deleteuser";
  constructor( private http: HttpClient) { }
  
  addUser (json): Observable<any> {
    return this.http.post<any>(this.url1, json, httpOptions);
  }

  getAllUsers(): Observable<any> {
    return this.http.get<any>(this.url2);
  }

  deleteUser (email): Observable<any> {
    return this.http.post<any>(this.url3, email, httpOptions);
  }
  
}
