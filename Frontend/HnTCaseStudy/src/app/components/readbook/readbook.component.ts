import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-readbook',
  templateUrl: './readbook.component.html',
  styleUrls: ['./readbook.component.css']
})
export class ReadbookComponent implements OnInit {

  readBookData:any={
    bookname:this.data.bookname,
    authorname:this.data.authorname,
    contents:this.data.contents
  }
  constructor(@Inject(MAT_DIALOG_DATA)public data:any) { }

  ngOnInit(): void {
  }

}
