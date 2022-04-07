import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Events } from 'src/app/shared/entity/Event';

@Injectable({
  providedIn: 'root'
})
export class ShowAllEventsService {

  private baseUrl = "http://localhost:8080/wedding";

  constructor(private http: HttpClient) { }

  getAllEvents(customerId: number): Observable<Events[]> {
    return this.http.get<Events[]>(this.baseUrl + "/getallevents/" + customerId);
  }

  deleteEvent(eventId: number): Observable<string> {
    return this.http.delete<string>(this.baseUrl + "/deleteevent/" + eventId);
  }

  updateEvent(event: Events): Observable<Events> {
    return this.http.post<Events>((this.baseUrl + "/updateevent"), event);
  }
}
