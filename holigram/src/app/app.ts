import { Component, computed, signal } from '@angular/core';
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

  planets = signal<any[]>([]);
  shortestPath = signal<any>(null);
  enableTraffic = signal(false);
  private lastSelectedId: number | null = null;

    constructor(private http: HttpClient) {}

  ngOnInit() {
    this.http.get<any[]>('http://localhost:8080/planetsWithRoutes')
      .subscribe({
        next: (data) => {
          const planetsWithSelection = [];
          for (let i = 0; i < data.length; i++) {
            planetsWithSelection.push({
              ...data[i],
              selected: false
            });
          }
          this.planets.set(planetsWithSelection);
        },
        error: (err) => {
          console.error(err);
        }
      });
  }

  onEnableTrafficChanged(event: Event) {
    this.enableTraffic.set((event.target as HTMLInputElement).checked);
  }

  onPlanetChecked(planet: any, checked: boolean) {
    const planets = this.planets();

    planets.forEach(p => {
      if (p.planetId === planet.planetId) {
        p.selected = true;
      }
    });

    const selectedCount = planets.filter(p => p.selected);

    console.log(selectedCount.length);

    if(selectedCount.length <= 2) {
      this.lastSelectedId = planet.planetId;
      this.planets.set([...planets]);
      return;
    }

    planets.forEach(p => {
      if(p.planetId === this.lastSelectedId) {
        p.selected = false;
      }
    });

    this.lastSelectedId = planet.planetId;
    this.planets.set([...planets]);
  }

  onCalculateClicked() {
    const currentPlanets = this.planets();
    const selected = [];
    for (let i = 0; i < currentPlanets.length; i++) {
      if (currentPlanets[i].selected) {
        selected.push(currentPlanets[i]);
      }
    }

    if (selected.length !== 2) {
      return;
    }

    const originId = selected[0].planetId;
    const destinationId = selected[1].planetId;

    this.http.get<any>(`http://localhost:8080/shortestPathBetweenPlanets?originPlanetId=${originId}&destinationPlanetId=${destinationId}&includeTrafficDelay=${this.enableTraffic()}`)
      .subscribe({
        next: (data) => {
          this.shortestPath.set(data);
        },
        error: (err) => {
          console.error(err);
        }
      });
  }

  selectedNodesDisplay = computed(() => {
    const currentPlanets = this.planets();

    let display = '';
    for (let i = 0; i < currentPlanets.length; i++) {
      if (currentPlanets[i].selected) {
        if (display) {
          display += ', ';
        }
        display += currentPlanets[i].planetNode;
      }
    }

    return display;
  });

  pathDisplay = computed(() => {
    const path = this.shortestPath();

    if (!path) {
      return '';
    }

    let display = '';
    for (let i = 0; i < path.planetPath.length; i++) {
      if (i > 0) {
        display += ' -> ';
      }
      display += path.planetPath[i].node;
    }

    return display;
  });

  namePathDisplay = computed(() => {
    const path = this.shortestPath();

    if (!path) {
      return '';
    }

    let display = '';
    for (let i = 0; i < path.planetPath.length; i++) {
      if (i > 0) {
        display += ' -> ';
      }
      display += path.planetPath[i].name;
    }

    return display;
  });
}
