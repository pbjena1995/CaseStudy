import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

const bookUrl="http://localhost:8095/book";
const readerUrl="http://localhost:8095/reader"
@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http:HttpClient) { }

  saveBookData(bookData:any){
    return this.http.post(bookUrl,bookData,{responseType: 'text'});
  }

  getBookdata(){
    return this.http.get(bookUrl);
  }

  getActiveBookdata(){
    return this.http.get(bookUrl+"/activebook");
  }

  updateStatus(id:number){
    const body = { title: 'Angular PUT Request Example' };
    return this.http.put(bookUrl+"/"+id,body,{responseType: 'text'});
  }

  editBookData(editBookData:any){
      return this.http.put(bookUrl,editBookData,{responseType: 'text'});
  }

  buyBook(buyBookData:any){
      return this.http.post(readerUrl+"/buyBook",buyBookData,{responseType: 'text'});
  }

  getPurchasedBooks(email:string){
      return  this.http.get(readerUrl+"/"+email)
  }
  getBookByAuthor(authorName:string){
      return this.http.get(bookUrl+"/author/"+authorName);
  }
  getBookByPublisher(publisher:string){
    return this.http.get(bookUrl+"/publisher/"+publisher);
  }
  getBookByCategory(category:string){
    return this.http.get(bookUrl+"/category/"+category);
  }

  bookRefund(email:string,bookId:number){
    return this.http.post(readerUrl+"/refund/"+email+"/"+bookId,'',{responseType: 'text'});
  }
}
