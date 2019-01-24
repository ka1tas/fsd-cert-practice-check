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
  url2: string = "/signup/token";
 
  constructor( private http: HttpClient) { }
  
  addUser (json): Observable<any> {
    return this.http.post<any>(this.url1, json, httpOptions);
  }



  getToken (json): Observable<any> {
    console.log("inside getToken");
    console.log("inside getToken:"+json);
    return this.http.post<any>(this.url2, json, httpOptions);
  }
  
}
