import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { SignupService } from '../signup.service';
import { TransfereService } from '../transfere.service';
import { TokenService } from '../token.service';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  users: any;
  json: any;
  token:any;
  tokenjson:any;
  status={
    signupStatus:false,
    emailExist:false
  };
  success=false;
  error=false;

  signup = new FormGroup({
    name: new FormControl(''),
    email: new FormControl(''),
    password: new FormControl(''),
  });


  constructor(public service: SignupService, public tokeService: TokenService, public transfereService: TransfereService) { }
  ngOnInit(
   
  ) {

    this.tokenjson=  {
      "userName":"saikat",
      "id":"5465465",
      "role":"adminan"
      };

      this.tokeService.getToken(this.tokenjson).subscribe(data=>{
        console.log(data);},
        error=>{
        this.token=error.error.text;
        console.log("Token: "+this.token)
      }); 

     /*  this.token=  this.transfereService.getToken();
      console.log(this.token);*/

  }



  addUser() {
    this.json = this.signup.value;
    console.log(this.json);
    this.service.addUser(this.json).subscribe(data => {   
      this.ngOnInit();
      this.status=data;
      this.error=false;
      console.log(this.status);
      if(this.status.signupStatus==true){
      this.signup.reset();
      }
    },
    error=>{

      this.error=true;
      this.status.signupStatus=false;
      this.status.emailExist=false;
    }
    
    );

  }
  

}
