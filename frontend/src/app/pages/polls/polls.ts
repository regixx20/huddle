import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';

import { MatToolbarModule } from '@angular/material/toolbar';
import { MatListModule } from '@angular/material/list';

@Component({
  selector: 'app-polls',
  standalone: true,
  imports: [CommonModule, MatToolbarModule, MatListModule],
  template: `
    <mat-toolbar color="primary">Meet Easy</mat-toolbar>

    <mat-nav-list>
      <a mat-list-item *ngFor="let p of polls">
        {{ p.title }} — {{ p.location }}
      </a>
    </mat-nav-list>
  `,
})
export class PollsComponent implements OnInit {
  polls: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.http.get<any[]>('/api/polls').subscribe(data => this.polls = data);
  }
}
