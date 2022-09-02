import { Component, OnInit } from '@angular/core';
import { BookService } from '../book.service';

@Component({
  selector: 'app-createbook',
  templateUrl: './createbook.component.html',
  styleUrls: ['./createbook.component.css']
})
export class CreatebookComponent implements OnInit {


  book={
    title:'Java Book',
    category:'CS',
    price:'1000',
    publisher:'abc',
    contents:'This is java book'
  }

  constructor(public bookService:BookService) { }

  saveBook(){
    console.log("Clicked by the user");
    const promise=this.bookService.save(this.book);
    promise.subscribe((responseBody:any)=>{
        console.log("Book Saved Succesfully"+responseBody);
    })
  }

  ngOnInit(): void {
  }

}
