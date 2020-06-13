import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EtatregSharedModule } from 'app/shared/shared.module';
import { SecteurActiviteComponent } from './secteur-activite.component';
import { SecteurActiviteDetailComponent } from './secteur-activite-detail.component';
import { SecteurActiviteUpdateComponent } from './secteur-activite-update.component';
import { SecteurActiviteDeleteDialogComponent } from './secteur-activite-delete-dialog.component';
import { secteurActiviteRoute } from './secteur-activite.route';

@NgModule({
  imports: [EtatregSharedModule, RouterModule.forChild(secteurActiviteRoute)],
  declarations: [
    SecteurActiviteComponent,
    SecteurActiviteDetailComponent,
    SecteurActiviteUpdateComponent,
    SecteurActiviteDeleteDialogComponent,
  ],
  entryComponents: [SecteurActiviteDeleteDialogComponent],
})
export class EtatregSecteurActiviteModule {}
