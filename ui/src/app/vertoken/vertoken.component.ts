import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { SignupService } from '../signup.service';

@Component({
  selector: 'app-vertoken',
  templateUrl: './vertoken.component.html',
  styleUrls: ['./vertoken.component.css']
})
export class VertokenComponent implements OnInit {

    error:any;
    json:any;
    jwtToken:any;

  constructor(public service: SignupService) { }

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
      this.jwtToken=data;
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
      
      console.log(error.error.text);
      
    }
    }
    
    );

  }
  


}
