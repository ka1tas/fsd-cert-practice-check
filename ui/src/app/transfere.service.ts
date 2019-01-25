import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class TransfereService {

 
  constructor(
    private router:Router
  ) { 
    console.log("transfere service constructor");
  }

  private token;

  setToken(token){
    this.token = token;
    console.log("data in transfer");
    console.log(token);
  }

  getToken(){
    return this.token;
  }

  clearToken(){
    this.token = undefined;
  }

}