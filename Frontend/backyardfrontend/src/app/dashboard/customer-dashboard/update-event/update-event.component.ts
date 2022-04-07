import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Events } from 'src/app/shared/entity/Event';

@Component({
  selector: 'app-update-event',
  templateUrl: './update-event.component.html',
  styleUrls: []
})
export class UpdateEventComponent implements OnInit {
  successMsg: string;
  errMsg: string;

  updateEventForm: FormGroup;

  @Input() event: Events;
  @Input() isOpened: true;
  @Output() notify = new EventEmitter;
  
  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {

  }

  updateEvent() {
    this.notify.emit(this.event);
    this.notify.emit(this.isOpened);
  }
}
