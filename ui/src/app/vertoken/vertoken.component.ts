import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { SignupService } from '../signup.service';
import { TokenService } from '../token.service';
import { TransfereService } from '../transfere.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-vertoken',
  templateUrl: './vertoken.component.html',
  styleUrls: ['./vertoken.component.css']
})
export class VertokenComponent implements OnInit {

    error:any;
    json:any;
    jwtToken:any;

  constructor(public service: TokenService, private router: Router, public transfereService: TransfereService) { }

  verifytoken = new FormGroup({
    name: new FormControl(''),
    id: new FormControl(''),
    role: new FormControl(''),
  });


  ngOnInit() {
  }

  authenticate() {
    this.json = this.verifytoken.value;
    console.log(this.json);
    console.log("inside authenticate");
    this.service.getToken(this.json).subscribe(data => {   
   
      console.log("saikat");
    },
    error=>{
      if(error.status=!200){
      console.log("errrrrr");
      this.error=true;
      console.log(error);
    }
    else{
      this.jwtToken=error.error.text;    
      console.log("statsus"+ error); 
      console.log(error.error.text);    
      this.transfereService.setToken(this.jwtToken); 
      this.router.navigate(['/signup']);
    }
    }
    );
  }
  
}
