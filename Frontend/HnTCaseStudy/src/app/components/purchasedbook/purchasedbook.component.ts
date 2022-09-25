import { Component, OnInit } from '@angular/core';
import { BookService } from 'src/app/service/book.service';
import { MatDialog,MatDialogConfig } from '@angular/material/dialog';
import { ReadbookComponent } from '../readbook/readbook.component';

@Component({
  selector: 'app-purchasedbook',
  templateUrl: './purchasedbook.component.html',
  styleUrls: ['./purchasedbook.component.css']
})
export class PurchasedbookComponent implements OnInit {

  searchData:any={
      email:''
  }
  purchasedBookData:any[]=[];
  buyBookError=false;
  constructor(private bookService:BookService,private matDialog:MatDialog) { }

  ngOnInit(): void {
  }

  purchasedBooks(){
    console.log("inside purchasedBooks()");
    if(this.searchData.email != ''){
      this.bookService.getPurchasedBooks(this.searchData.email).subscribe(
        (responseBody:any)=>{
            this.buyBookError=false;
            this.purchasedBookData=responseBody;
        },
        (error:any)=>{
          this.buyBookError=true;
        }
      );
    }
    
  }

  refundBook(bookId:number){
    if(this.searchData.email != ''){
      this.bookService.bookRefund(this.searchData.email,bookId).subscribe(
        (responseBody:any)=>{
          alert(responseBody);
          this.purchasedBooks();
        },
        (error:any)=>{
          alert(error);
        }
      );
    }
  }

  readBook(readBookData:any){
    console.log("Inside buyBook()")
    this.matDialog.open(ReadbookComponent,{width:'40%',height:'300px',
    data:{
       bookname:readBookData.bookname,
       authorname:readBookData.authorname,
       contents:readBookData.contents
    }
  });
  }
}
