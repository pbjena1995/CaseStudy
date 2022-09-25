import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

const authorUrl="http://localhost:8096/author";
@Injectable({
  providedIn: 'root'
})
export class LoginService {


  constructor(private http:HttpClient) { }

  loginUser(requestUser:any){
    return this.http.post(authorUrl+"/signin",requestUser);
  }

  signUpUser(requestsignupUser:any){
    return this.http.post(authorUrl+"/signup",requestsignupUser,{responseType: 'text'});
  }

  setToken(token:string){
      localStorage.setItem("token",token);
  }

  isLogIn(){
    let token=localStorage.getItem("token");
    if(token == undefined || token ==='' || token == null){
        return false;
    }
    return true;
  }

  logout(){
    localStorage.removeItem("token");
    return true;
  }


}
