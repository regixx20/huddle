import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, firstValueFrom } from 'rxjs';

export type MeDto = {
  authenticated: boolean;
  user: null | {
    id: number;
    email: string;
    firstName: string;
    lastName: string;
    fullName: string;
  };
};

@Injectable({ providedIn: 'root' })
export class AuthService {
  private meSubject = new BehaviorSubject<MeDto | null>(null);
  me$ = this.meSubject.asObservable();

  constructor(private http: HttpClient) {}

  async refreshMe(): Promise<MeDto> {
    const me = await firstValueFrom(this.http.get<MeDto>('/api/me'));
    this.meSubject.next(me);
    return me;
  }

  get snapshot(): MeDto | null {
    return this.meSubject.value;
  }
}
