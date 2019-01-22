import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { SignupService } from '../signup.service';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  users: any;
  json: any;
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


  constructor(public service: SignupService) { }
  ngOnInit() {

    /*   this.service.getAllUsers().subscribe(data=>{
        console.log(data);
        this.users=data;
      }); */
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
