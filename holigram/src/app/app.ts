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
  private selectionCounter = 0;

    constructor(private http: HttpClient) {}

  ngOnInit() {
    this.http.get<any[]>('http://localhost:8080/planetsWithRoutes')
      .subscribe({
        next: (data) => {
          this.planets = data.map(planet => ({
            ...planet,
            selected: false
          }));
        },
        error: (err) => {
          console.error(err);
        }
      });
  }

  onPlanetChecked(planet: any, checked: boolean) {
    planet.selected = checked;

    if (checked) {
      planet.selectedAt = ++this.selectionCounter;

      const selected = this.planets
        .filter(p => p.selected)
        .sort((a, b) => a.selectedAt - b.selectedAt);

      if (selected.length > 2) {
        selected[1].selected = false;
      }
    }
  }

  onCalculateClicked() {
    console.log('calculating');
  }
}
