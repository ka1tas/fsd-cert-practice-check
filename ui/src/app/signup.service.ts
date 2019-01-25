import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, config } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';
import { TransfereService } from './transfere.service';




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
  
 
  constructor( private http: HttpClient) { }
  
  addUser (json): Observable<any> {
    return this.http.post<any>(this.url1, json, httpOptions);
  }



 
  
}
