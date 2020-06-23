import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EtatregSharedModule } from 'app/shared/shared.module';
import { PeriodeComponent } from './periode.component';
import { PeriodeDetailComponent } from './periode-detail.component';
import { PeriodeUpdateComponent } from './periode-update.component';
import { PeriodeDeleteDialogComponent } from './periode-delete-dialog.component';
import { periodeRoute } from './periode.route';

@NgModule({
  imports: [EtatregSharedModule, RouterModule.forChild(periodeRoute)],
  declarations: [PeriodeComponent, PeriodeDetailComponent, PeriodeUpdateComponent, PeriodeDeleteDialogComponent],
  entryComponents: [PeriodeDeleteDialogComponent],
})
export class EtatregPeriodeModule {}
