import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SignupComponent } from './signup/signup.component';
import { VertokenComponent } from './vertoken/vertoken.component';

const routes: Routes = [

  { path:"" , component:SignupComponent },
  { path:"signup" , component:SignupComponent },
  { path:"verifytoken" , component:VertokenComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
