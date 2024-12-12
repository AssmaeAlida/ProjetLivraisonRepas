import { Component, OnInit ,EventEmitter, Output} from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  @Output() menuSelect = new EventEmitter<string>();

  selectMenu(option: string) {
    this.menuSelect.emit(option);
  }
  constructor() { }

  ngOnInit(): void {
  }

}
