import {Component, inject} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AsyncPipe} from '@angular/common';
import {catchError, map, Observable, of} from 'rxjs';
import {Account, AccountListState, RequestStatus} from '../model/account.model';

@Component({
  selector: 'app-accounts',
  imports: [
    AsyncPipe
  ],
  templateUrl: './accounts.component.html',
  styleUrl: './accounts.component.css'
})
export class AccountsComponent {
  private http = inject(HttpClient);
  accounts$:Observable<AccountListState> = this.http.get<Account[]>
  ('http://localhost:9999/EBANK-SERVICE/accounts').pipe(
    map(resp=>{
      return {accounts: resp, status:RequestStatus.SUCCESS}
    }),
    catchError((err,caught) => {
      return of({status:RequestStatus.ERROR, message:err.statusText})
    })
  );

  protected readonly RequestStatus = RequestStatus;
}
