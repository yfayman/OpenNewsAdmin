import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css']
})
export class SideBarComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  clicked(event) {
    let target = event.target;
    var otherLi = event.target.parentNode.parentNode.children;
    for (var i = 0; i < otherLi.length; i++) {
      otherLi[i].classList.remove("active");
    }
    target.parentNode.className += 'active';
  }

}
