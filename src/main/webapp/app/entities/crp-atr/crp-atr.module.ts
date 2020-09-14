import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EtatregSharedModule } from 'app/shared/shared.module';
import { CrpAtrComponent } from './crp-atr.component';
import { CrpAtrDetailComponent } from './crp-atr-detail.component';
import { CrpAtrUpdateComponent } from './crp-atr-update.component';
import { CrpAtrDeleteDialogComponent } from './crp-atr-delete-dialog.component';
import { crpAtrRoute } from './crp-atr.route';

@NgModule({
  imports: [EtatregSharedModule, RouterModule.forChild(crpAtrRoute)],
  declarations: [CrpAtrComponent, CrpAtrDetailComponent, CrpAtrUpdateComponent, CrpAtrDeleteDialogComponent],
  entryComponents: [CrpAtrDeleteDialogComponent],
})
export class EtatregCrpAtrModule {}
