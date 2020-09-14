import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EtatregSharedModule } from 'app/shared/shared.module';
import { ChargeFileComponent } from './charge-file.component';
import { ChargeFileDetailComponent } from './charge-file-detail.component';
import { ChargeFileUpdateComponent } from './charge-file-update.component';
import { ChargeFileDeleteDialogComponent } from './charge-file-delete-dialog.component';
import { chargeFileRoute } from './charge-file.route';

@NgModule({
  imports: [EtatregSharedModule, RouterModule.forChild(chargeFileRoute)],
  declarations: [ChargeFileComponent, ChargeFileDetailComponent, ChargeFileUpdateComponent, ChargeFileDeleteDialogComponent],
  entryComponents: [ChargeFileDeleteDialogComponent],
})
export class EtatregChargeFileModule {}
