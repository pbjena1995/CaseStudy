import { Component, OnInit } from '@angular/core';
import { MatDialog,MatDialogConfig } from '@angular/material/dialog';
import { BookService } from 'src/app/service/book.service';
import { EditBookComponent } from '../edit-book/edit-book.component';

@Component({
  selector: 'app-authordashboard',
  templateUrl: './authordashboard.component.html',
  styleUrls: ['./authordashboard.component.css']
})
export class AuthordashboardComponent implements OnInit {


  getBookData:any[]=[];

  constructor(private bookService:BookService,private matDialog:MatDialog) { }

  ngOnInit(): void {
    this.getBookdata();
  }

  getBookdata(){
      console.log("inside getBookdata()");
      this.bookService.getBookdata().subscribe(
        (responseBody:any)=>{
            this.getBookData=responseBody;
        },
        (error:any)=>{
          alert(error)
        }
      );
  }

  updateStatus(id:number){
    console.log("Inside editButtonClick()"+id);
    this.bookService.updateStatus(id).subscribe(
      (responseBody:any)=>{
        window.location.href="authordashboard";
        alert(responseBody);
      },
      (error:any)=>{
        alert(error)
      }
    );    
  }

  editButtonClick(receiveEditData:any){
    console.log("Inside editButtonClick"+receiveEditData.id);
    this.matDialog.open(EditBookComponent,{width:'60%',height:'400px',
    data:{
        id:receiveEditData.id,
        bookname:receiveEditData.bookname,
        title:receiveEditData.title,
        category:receiveEditData.category,
        price:receiveEditData.price,
        authorname:receiveEditData.authorname,
        publisher:receiveEditData.publisher,
        contents:receiveEditData.contents
    }
  });
  }

}
