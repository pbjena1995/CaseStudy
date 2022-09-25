import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { BookService } from 'src/app/service/book.service';

@Component({
  selector: 'app-edit-book',
  templateUrl: './edit-book.component.html',
  styleUrls: ['./edit-book.component.css']
})
export class EditBookComponent implements OnInit {

  editbookdata:any={
    id:this.data.id,
    bookname:this.data.bookname,
    title:this.data.title,
    category:this.data.category,
    price:this.data.price,
    authorname:this.data.authorname,
    publisher:this.data.publisher,
    contents:this.data.contents
  }
  editBookForm:FormGroup;
  constructor(private bookService:BookService ,@Inject(MAT_DIALOG_DATA)public data:any) { }

  ngOnInit() {
    this.editBookForm =new FormGroup({
      bookname:new FormControl('',[Validators.required]),
      title:new FormControl('',[Validators.required]),
      category:new FormControl('',[Validators.required]),
      price:new FormControl('',[Validators.required]),
      authorname:new FormControl('',[Validators.required]),
      publisher:new FormControl('',[Validators.required]),
      contents:new FormControl('',[Validators.required]),
  });
  }

  public myError = (controlName: string, errorName: string) =>{
    return this.editBookForm.controls[controlName].hasError(errorName);
    }

  editBookSave(){
    console.log("inside editBookSave()!!!")
    if(this.editBookForm.valid){
      this.bookService.editBookData(this.editbookdata).subscribe(
        (responseBody:any)=>{
          console.log("Success!!!");
          window.location.href="authordashboard";
        },
        (error:any)=>{
          console.log(error);
        }
      )
    }
   
  }

}
