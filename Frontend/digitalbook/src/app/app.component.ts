import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  book={
    title:'Java Book',
    category:'CS',
    price:'1000',
    publisher:'abc',
    contents:'This is java book'
  }
  title = 'digitalbook';
}
