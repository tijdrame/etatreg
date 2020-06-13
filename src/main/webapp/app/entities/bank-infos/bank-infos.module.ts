import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EtatregSharedModule } from 'app/shared/shared.module';
import { BankInfosComponent } from './bank-infos.component';
import { BankInfosDetailComponent } from './bank-infos-detail.component';
import { BankInfosUpdateComponent } from './bank-infos-update.component';
import { BankInfosDeleteDialogComponent } from './bank-infos-delete-dialog.component';
import { bankInfosRoute } from './bank-infos.route';

@NgModule({
  imports: [EtatregSharedModule, RouterModule.forChild(bankInfosRoute)],
  declarations: [BankInfosComponent, BankInfosDetailComponent, BankInfosUpdateComponent, BankInfosDeleteDialogComponent],
  entryComponents: [BankInfosDeleteDialogComponent],
})
export class EtatregBankInfosModule {}
