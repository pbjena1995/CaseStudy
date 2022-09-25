import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/service/login.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signup:any={
    username:'',
    email:'',
    password:''

  }
  signupForm:FormGroup;
  signUpError=false;
  constructor(private loginservice:LoginService) { }

  ngOnInit() {
    this.signupForm =new FormGroup({
      username:new FormControl('',[Validators.required,Validators.maxLength(20)]),
      email:new FormControl('',[Validators.required,Validators.maxLength(30)]),
      password:new FormControl('',[Validators.required,Validators.maxLength(10)])
  });
  }

  public myError = (controlName: string, errorName: string) =>{
    return this.signupForm.controls[controlName].hasError(errorName);
    }
  register(){
    console.log("inside onsubmit()!!!")
    if(this.signupForm.valid){
      this.loginservice.signUpUser(this.signup).subscribe(
        result=>{
            window.location.href="login";
        },
        error=>{
          //window.location.href="signup";
          this.signUpError=true;
        }
      );
    }
  }

}
