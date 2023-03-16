import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  path:String='../assets/images/logo_ooredoo.png';
  altText:String="Logo Ooredoo";
  constructor() { }

  ngOnInit(): void {
  }

}
