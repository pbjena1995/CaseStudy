import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  constructor(private loginService:LoginService) { }

  ngOnInit(): void {

    this.isLoggedIn=this.loginService.isLogIn();
  }

  isLoggedIn=false;
  userLogout(){
      this.loginService.logout();
      window.location.href="login";
  }



}
