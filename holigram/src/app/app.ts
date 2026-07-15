import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('holigram');

  planets: any[] = [];

    constructor(private http: HttpClient) {}

  ngOnInit() {
    this.http.get<any[]>('http://localhost:8080/planetsWithRoutes')
      .subscribe({
        next: (data) => {
          this.planets = data;
          console.log(data);
        },
        error: (err) => {
          console.error(err);
        }
      });
  }
}
