import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EtatregSharedModule } from 'app/shared/shared.module';
import { ObjetCreditComponent } from './objet-credit.component';
import { ObjetCreditDetailComponent } from './objet-credit-detail.component';
import { ObjetCreditUpdateComponent } from './objet-credit-update.component';
import { ObjetCreditDeleteDialogComponent } from './objet-credit-delete-dialog.component';
import { objetCreditRoute } from './objet-credit.route';

@NgModule({
  imports: [EtatregSharedModule, RouterModule.forChild(objetCreditRoute)],
  declarations: [ObjetCreditComponent, ObjetCreditDetailComponent, ObjetCreditUpdateComponent, ObjetCreditDeleteDialogComponent],
  entryComponents: [ObjetCreditDeleteDialogComponent],
})
export class EtatregObjetCreditModule {}
