import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddBookComponent } from './components/add-book/add-book.component';
import { AuthordashboardComponent } from './components/authordashboard/authordashboard.component';
import { EditBookComponent } from './components/edit-book/edit-book.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { PurchasedbookComponent } from './components/purchasedbook/purchasedbook.component';
import { ReaderComponent } from './components/reader/reader.component';
import { SignupComponent } from './components/signup/signup.component';
import { AuthGuard } from './service/auth.guard';

const routes: Routes = [
  { path: "", component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  {path: 'authordashboard', component: AuthordashboardComponent,canActivate:[AuthGuard]},
  {path:"add-book",component:AddBookComponent},
  {path:"reader",component:ReaderComponent},
  {path:"editbook",component:EditBookComponent},
  {path:"purchasedbook",component:PurchasedbookComponent}
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
