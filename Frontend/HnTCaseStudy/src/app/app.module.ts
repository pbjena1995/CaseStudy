import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatToolbarModule} from '@angular/material/toolbar';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AuthordashboardComponent } from './components/authordashboard/authordashboard.component';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import { AddBookComponent } from './components/add-book/add-book.component';
import {MatSelectModule} from '@angular/material/select';
import { ReaderComponent } from './components/reader/reader.component';
import { HomeComponent } from './components/home/home.component';
import {ReactiveFormsModule} from '@angular/forms';
import {MatMenuModule} from '@angular/material/menu';
import {MatTableModule} from '@angular/material/table';
import {MatDialogModule} from '@angular/material/dialog';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { EditBookComponent } from './components/edit-book/edit-book.component';
import { BuybookComponent } from './components/buybook/buybook.component';
import { PurchasedbookComponent } from './components/purchasedbook/purchasedbook.component';
import { ReadbookComponent } from './components/readbook/readbook.component';




@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    LoginComponent,
    SignupComponent,
    AuthordashboardComponent,
    AddBookComponent,
    ReaderComponent,
    HomeComponent,
    EditBookComponent,
    BuybookComponent,
    PurchasedbookComponent,
    ReadbookComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,MatToolbarModule,MatButtonModule,MatIconModule,MatCardModule,MatInputModule,
    MatFormFieldModule,FormsModule,HttpClientModule,MatSidenavModule,MatListModule,MatSelectModule,ReactiveFormsModule,
    MatMenuModule,MatTableModule, NgbModule,MatDialogModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents:[EditBookComponent]
})
export class AppModule { }
