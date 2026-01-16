
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';

import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterModule, MatButtonModule, MatCardModule],
  template: `
    <div style="max-width: 900px; margin: 24px auto; padding: 0 16px;">
      <div style="display: grid; gap: 16px; grid-template-columns: repeat(auto-fit, minmax(260px, 1fr));">
        <mat-card>
          <mat-card-title>Créer un nouveau sondage</mat-card-title>
          <mat-card-content style="margin-top: 8px;">
            <p>Organise un rendez-vous en quelques clics.</p>
          </mat-card-content>
          <mat-card-actions>
            <button mat-raised-button color="primary"
              (click)="goCreatePoll()">
              Créer sondage
            </button>
          </mat-card-actions>
        </mat-card>

        <mat-card *ngIf="me?.authenticated">
          <mat-card-title>Accéder à mon espace</mat-card-title>
          <mat-card-content style="margin-top: 8px;">
            <p>Voir mes sondages et mes participations.</p>
          </mat-card-content>
          <mat-card-actions>
            <a mat-raised-button color="accent" routerLink="/dashboard">
              Mon espace
            </a>
          </mat-card-actions>
        </mat-card>
      </div>

      <div style="margin-top: 28px;">
        <h2>Pourquoi utiliser MeetEasy ?</h2>
        <ul>
          <li>Créer des sondages de dispo en équipe</li>
          <li>Éviter 50 messages et trouver un créneau rapidement</li>
          <li>Voir les réponses en temps réel</li>
        </ul>

        <h2 style="margin-top: 18px;">Comment utiliser MeetEasy ?</h2>
        <ol>
          <li>Créer un sondage</li>
          <li>Partager le lien</li>
          <li>Collecter les disponibilités</li>
          <li>Décider du créneau</li>
        </ol>
      </div>
    </div>
  `
})
export class HomeComponent implements OnInit {

  me: any = null; // ← déclaration simple, sans logique

  constructor(private auth: AuthService) {}

  async ngOnInit() {
    this.me = await this.auth.refreshMe();
  }

  goCreatePoll() {
    if (this.me?.authenticated) {
      window.location.href = 'http://localhost:8080/meeting/edit';
    } else {
      window.location.href = 'http://localhost:8080/register';
    }
  }
}

