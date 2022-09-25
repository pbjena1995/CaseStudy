import { Component, OnInit } from '@angular/core';
import { BookService } from 'src/app/service/book.service';
import { MatDialog,MatDialogConfig } from '@angular/material/dialog';
import { BuybookComponent } from '../buybook/buybook.component';
import { FormGroup } from '@angular/forms';


@Component({
  selector: 'app-reader',
  templateUrl: './reader.component.html',
  styleUrls: ['./reader.component.css']
})
export class ReaderComponent implements OnInit {

  searchBookData:any={
    category:'',
    value:''
  }
  getBookData:any[]=[];
  constructor(private bookService:BookService,private matDialog:MatDialog) { }

  ngOnInit(): void {
    this.getBookdata();
  }

  getBookdata(){
    console.log("inside getBookdata()");
    this.bookService.getActiveBookdata().subscribe(
      (responseBody:any)=>{
          this.getBookData=responseBody;
      },
      (error:any)=>{
        alert(error)
      }
    );
}

buyBook(bookId:number){
  console.log("Inside buyBook()"+bookId)
  this.matDialog.open(BuybookComponent,{width:'25%',height:'300px',
  data:{
      id:bookId
  }
});
}

searchBookByCategory(){
  console.log("Inside searchBookByCategory()")
  if(this.searchBookData.category != '' && this.searchBookData.value != ''){
    if(this.searchBookData.category == "AUTHOR"){
      console.log("Inside searchBookByAuthor");
      this.bookService.getBookByAuthor(this.searchBookData.value).subscribe(
        (responseBody:any)=>{
            this.getBookData=responseBody;
        },
        (error:any)=>{
          alert(error)
        }
      );
    }
    else if(this.searchBookData.category == "CATEGORY"){
      console.log("Inside searchBookByCategory")
      this.bookService.getBookByCategory(this.searchBookData.value).subscribe(
        (responseBody:any)=>{
            this.getBookData=responseBody;
        },
        (error:any)=>{
          alert(error)
        }
      );
    }
    else{
      console.log("Inside searchBookByPublisher")
      this.bookService.getBookByPublisher(this.searchBookData.value).subscribe(
        (responseBody:any)=>{
            this.getBookData=responseBody;
        },
        (error:any)=>{
          alert(error)
        }
      );
    }
  }
}
}
