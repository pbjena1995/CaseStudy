import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'HnTCaseStudy';
  bookIcon:string="assets/images/logo3.png";

  flag=true;
  tuggle(){
      this.flag=!this.flag;
  }
}
