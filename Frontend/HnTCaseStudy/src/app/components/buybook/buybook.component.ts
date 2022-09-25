import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { BookService } from 'src/app/service/book.service';

@Component({
  selector: 'app-buybook',
  templateUrl: './buybook.component.html',
  styleUrls: ['./buybook.component.css']
})
export class BuybookComponent implements OnInit {

  buyBookData:any={
    name:'',
    email:'',
    payment:[
      {
        book_id:this.data.id,
        payment_status:"DONE",
        refund_status:"NOTDONE"
      }
    ]
  }
  buyBookForm:FormGroup;
  constructor(private bookService:BookService,@Inject(MAT_DIALOG_DATA)public data:any) { }

  ngOnInit() {
    this.buyBookForm =new FormGroup({
      name:new FormControl('',[Validators.required]),
      email:new FormControl('',[Validators.required])
  });
  }

  public myError = (controlName: string, errorName: string) =>{
    return this.buyBookForm.controls[controlName].hasError(errorName);
    }
  buyBook( ){
    console.log("Inside buyBook()")

    if(this.buyBookForm.valid){
      this.bookService.buyBook(this.buyBookData).subscribe(
        (responseBody:any)=>{
            
            alert("Book Purchased Successfully !!!");
            window.location.href="reader"
        },
        (error:any)=>{
          alert(error)
        }
      );
    }
    
  }
}
