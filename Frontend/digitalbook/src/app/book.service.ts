import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


const url="http://localhost:8095/author/authorId/2"
@Injectable({
  providedIn: 'root'
})
export class BookService {

  save(book:any){
     return this.http.post(url,book);
  }


  constructor(public http:HttpClient) { }
}
