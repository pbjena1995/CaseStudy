import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/service/login.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  
  user:any={
    username:'',
    password:''
  }

  loginForm:FormGroup;
  constructor(private loginService:LoginService) { }
 
  ngOnInit(){

    this.loginForm =new FormGroup({
        username:new FormControl('',[Validators.required,Validators.maxLength(20)]),
        password:new FormControl('',[Validators.required,Validators.maxLength(10)])
    });
  }

  public myError = (controlName: string, errorName: string) =>{
    return this.loginForm.controls[controlName].hasError(errorName);
    }
loginError=false;
  loginCall(){
    console.log("inside onsubmit()!!!");
    this.loginError=false;
    if(this.loginForm.valid){
      this.loginService.loginUser(this.user).subscribe(
        (response:any)=>{
          console.log("login success!!!"+response.token);
          this.loginService.setToken(response.token);
          window.location.href="authordashboard";
        },
        error=>{
          console.log("Failure!!!"+error.status);
          this.loginError=true;
        }
      );
    }
  }


}
