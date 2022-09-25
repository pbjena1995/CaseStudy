import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { BookService } from 'src/app/service/book.service';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {

  addbookdata={
    bookname:'',
    title:'',
    category:'',
    price:'',
    authorname:'',
    publisher:'',
    contents:''
  }
  addBookForm:FormGroup;
  addBookError=false;
  constructor(private bookService:BookService) { }

  ngOnInit() {
    this.addBookForm =new FormGroup({
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
    return this.addBookForm.controls[controlName].hasError(errorName);
    }
  saveBook(){
    console.log("inside saveBook()!!!")
    if(this.addBookForm.valid){
      console.log("Valid addBookForm")
      this.bookService.saveBookData(this.addbookdata).subscribe(
        (responseBody:any)=>{
          console.log("Success!!!");
          window.location.href="authordashboard";
        },
        (error:any)=>{
          console.log(error);
          this.addBookError=true;
        }
      )
    }
    
  }

}
