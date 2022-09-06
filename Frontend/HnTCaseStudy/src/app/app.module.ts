import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { ReaderComponent } from './components/reader/reader.component';
import { SignupComponent } from './components/signup/signup.component';
import { SigninComponent } from './components/signin/signin.component';
import { HomeComponent } from './components/home/home.component';
import { AddbookComponent } from './components/addbook/addbook.component';
const routes:Routes=[
  {
    path:'reader',component:ReaderComponent
  },
  {
    path:'signin',component:SigninComponent
  },
  {
    path:'signup',component:SignupComponent
  },
  {
    path:'home',component:HomeComponent
  },
  {
    path:'addbook',component:AddbookComponent
  }
]
@NgModule({
  declarations: [
    AppComponent,
  
    ReaderComponent,
       SignupComponent,
       SigninComponent,
       HomeComponent,
       AddbookComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
